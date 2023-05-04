include(":libs:bisutils")
project(":libs:bisutils").projectDir = File("libs/bisutils-kt")

include(":lang:param")
project(":lang:param").projectDir = File("lang/param")

include(":lang:enforce")
project(":lang:enforce").projectDir = File("lang/enforce")

include(":vfs:pbo")
project(":vfs:pbo").projectDir = File("vfs/pbo")

include(":sdk:dayz")
project(":sdk:dayz").projectDir = File("sdk/dayz")

rootProject.name = "Enfusion-Workbench"
