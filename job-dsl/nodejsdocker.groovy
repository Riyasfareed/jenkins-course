job('NodeJS Docker test') {
    scm {
        git('https://github.com/Riyasfareed/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Riyas')
            node / gitConfigEmail('riyas.afm@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('riyasfareed/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
