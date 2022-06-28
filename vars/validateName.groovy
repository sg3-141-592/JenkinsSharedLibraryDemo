def call(String env, String repositoryName) {
    def config = readYaml text: libraryResource("namingConventions.yml")
    if(repositoryName ==~ config['RepositoryNames'][env][0]) {
        println("Valid repository name: ${repositoryName} for regex ${config['RepositoryNames'][env][0]}")
    } else {
        throw new Exception("The repository name ${repositoryName} is not valid for environment ${env}")
    }
}