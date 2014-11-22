#!/opt/squaredsoftware/bin/luaj
require("ScriptUtil/Linux")
require("ScriptUtil/Git")

local tmpDir         = "/tmp/"
local repoDir        = "luajUpdate/"
local repoDirAbsPath = tmpDir .. repoDir
local desktopDir     = "squaredsoftware-desktop/"
local buildCmd       = "../gradlew buildLuaInterpreter"
local libsDir        = "build/libs/"
local builtJarName   = "squaredsoftware-desktop-1.0.jar"
local newJarName     = "luajNew.jar"
local installDir     = "/opt/squaredsoftware/luaj/"

local main

local isInNeedOfUpdate = false

main = function()
  io.write("Start update\n")  
  Linux.deleteDir(repoDirAbsPath)
  Linux.createDir(repoDirAbsPath)
  Git.cloneSquaredSoftwareRepoTo(repoDirAbsPath)
  io.write("Almost there\n")  

  Execute.cd(repoDirAbsPath)
  Execute.cd(desktopDir)
  Execute.executeCmd(buildCmd)
  Linux.deleteFile(installDir .. newJarName)
  Linux.move(libsDir .. builtJarName, installDir .. newJarName)
  Linux.move(installDir .. newJarName, installDir .. "luaj.jar")
  io.write("Done\n")
end

main()
