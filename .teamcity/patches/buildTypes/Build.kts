package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build")) {
    check(buildNumberPattern == "%build.number%-%build.counter%") {
        "Unexpected option value: buildNumberPattern = $buildNumberPattern"
    }
    buildNumberPattern = "%build.counter%"
}
