task_branch = "${TEST_BRANCH_NAME}"
def branch_cutted = task_branch.contains("origin") ? task_branch.split('/')[1] : task_branch.trim()
currentBuild.displayName = "$branch_cutted"
base_git_url = "https://github.com/Vanloft/VK-final-project.git" // Ваш репозиторий

node {
    withEnv(["branch=${branch_cutted}", "base_url=${base_git_url}"]) {
        stage("Checkout Branch") {
            if (!"$branch_cutted".contains("main")) {  // Убедитесь, что используется правильная ветка
                try {
                    getProject("$base_git_url", "$branch_cutted")
                } catch (err) {
                    echo "Failed to get branch $branch_cutted"
                    throw ("${err}")
                }
            } else {
                echo "Current branch is main"
                getProject("$base_git_url", "$branch_cutted")
            }
        }

        try {
            stage("Run tests") {
                runTests()
            }
        } finally {
            stage("Allure") {
                generateAllure()
            }
        }
    }
}

def runTests() {
    try {
        bat 'cd Task_2 && .\\gradlew.bat clean test --debug'
    } finally {
        echo "Tests execution completed"
    }
}

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class: 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[url: repo]]
    ]
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'Task_2\\build\\allure-results']]  // Убедитесь, что это правильный путь к результатам Allure
    ])
}
