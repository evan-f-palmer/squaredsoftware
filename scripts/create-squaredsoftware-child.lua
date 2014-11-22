#!/opt/squaredsoftware/bin/luaj
require("ScriptUtil")

local newName                      = ""
local enclosingDir                 = ""
local gradleBuildFile              = "child/build.gradle"
local squaredSoftwareFolder        = "squaredsoftware/"
local squaredSoftwareScriptsFolder = squaredSoftwareFolder .. "scripts/"
local tarName                      = "child.tar"

local main

main = function()
  if(#arg == 2) then
      enclosingDir = arg[1]
      newName      = arg[2]
  else
      enclosingDir = Input.promptAndRead("ENTER the enclosing directory  : ")
      newName      = Input.promptAndRead("ENTER the new projects name    : ")
  end

  Linux.createDir(enclosingDir)
  Git.cloneSquaredSoftwareRepoTo(enclosingDir .. "/" ..squaredSoftwareFolder)
  Execute.cd(enclosingDir)
  Linux.copyFile(squaredSoftwareScriptsFolder .. tarName, " .")
  Linux.unTar(tarName)
  Linux.deleteFile(tarName) 
  Files.replaceStringWithNewStringInFile("child", newName, gradleBuildFile)
  Linux.move("child", newName)
end

main()
