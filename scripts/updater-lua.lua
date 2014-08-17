#!/opt/squaredsoftware/bin/lua
require("ScriptUtil")

local tmpDir         = "~/ws/tmp/"
local repoDir        = "luaUpdate/"
local repoDirAbsPath = tmpDir .. repoDir
local desktopDir     = "squaredsoftware-desktop/"
local buildCmd       = "../gradlew buildLuaInterpreter"
local libsDir        = "build/libs/"
local builtJarName   = "squaredsoftware-desktop-1.0.jar"
local newJarName     = "lua.jar"
local installDir     = "/opt/squaredsoftware/lua/"

local main

main = function()
  if(not Files.doesDirectoryExist(repoDirAbsPath)) then
    Linux.createDir(repoDirAbsPath)
    Git.cloneSquaredSoftwareRepoTo(repoDirAbsPath)
  elseif(not Git.isLocalRepoCommitIdTheSameAsTip(repoDirAbsPath)) then
    Linux.deleteDir(repoDirAbsPath)
    Linux.createDir(repoDirAbsPath)
    Git.cloneSquaredSoftwareRepoTo(repoDirAbsPath)
  end
  
  Execute.cd(repoDirAbsPath)
  Execute.cd(desktopDir)
  Execute.executeCmd(buildCmd)
  Linux.deleteFile(installDir .. newJarName)
  Linux.move(libsDir .. builtJarName, installDir .. newJarName)
end

main()