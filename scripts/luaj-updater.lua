#!/opt/squaredsoftware/bin/luaj
require("ScriptUtil")

local tmpDir         = "/tmp/"
local repoDir        = "luaUpdate/"
local repoDirAbsPath = tmpDir .. repoDir
local desktopDir     = "squaredsoftware-desktop/"
local buildCmd       = "../gradlew buildLuaInterpreter"
local libsDir        = "build/libs/"
local builtJarName   = "squaredsoftware-desktop-1.0.jar"
local newJarName     = "luajNew.jar"
local installDir     = "/opt/squaredsoftware/lua/"

local main

local isInNeedOfUpdate = false

main = function()
  if(not Files.doesDirectoryExist(repoDirAbsPath)) then
    Linux.createDir(repoDirAbsPath)
    Git.cloneSquaredSoftwareRepoTo(repoDirAbsPath)
    isInNeedOfUpdate = true
  else
    Execute.cd(repoDirAbsPath)
    Git.fetch()
    if(not Git.isLocalRepoCommitIdTheSameAsTip(repoDirAbsPath)) then
      Git.pull()
      isInNeedOfUpdate = true
    end
  end
  
  if(isInNeedOfUpdate) then
    Execute.cd(repoDirAbsPath)
    Execute.cd(desktopDir)
    Execute.executeCmd(buildCmd)
    Linux.deleteFile(installDir .. newJarName)
    Linux.move(libsDir .. builtJarName, installDir .. newJarName)
    Linux.move(installDir .. newJarName, installDir .. "luaj.jar")
  end
end

main()
