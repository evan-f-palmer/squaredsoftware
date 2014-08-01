#!/usr/bin/lua
local newName
local outputDir
local squaredSoftwareRepo          = "git@github.com:evan-f-palmer/squaredsoftware.git"
local gradleBuildFile              = "build.gradle"
local squaredSoftwareFolder        = "squaredsoftware/"
local squaredSoftwareScriptsFolder = squaredSoftwareFolder .. "scripts/"
local tarName                      = "child.tar"

local main
local promptForInput
local cloneSquaredSoftwareLib
local untar
local rename
local promptForInput
local cdToOutputDirThenExecute

main = function()
    outputDir = promptForInput("ENTER the output directory  : ")
    assert(os.execute("mkdir -p " .. outputDir))
    newName   = promptForInput("ENTER the new projects name : ")

    cloneSquaredSoftwareLib()
    untar()
    rename()
end

cloneSquaredSoftwareLib = function()
    cdToOutputDirThenExecute("git clone " .. squaredSoftwareRepo)
end

untar = function()
    cdToOutputDirThenExecute("cp " .. squaredSoftwareScriptsFolder .. tarName .. " .") 
    cdToOutputDirThenExecute("tar -zxf " .. tarName)
    cdToOutputDirThenExecute("rm " .. tarName)
end

rename = function()
    cdToOutputDirThenExecute("mv child " .. newName)
    local filePath = newName .. "/" .. gradleBuildFile
    cdToOutputDirThenExecute("sed -i \'s/child/" .. newName .. "/g\' " .. filePath)
end

cdToOutputDirThenExecute = function(xCmd)
    os.execute("cd " .. outputDir .. "; " .. xCmd)
end

promptForInput = function(xPrompt)
    io.write(xPrompt)
    io.flush()
    return io.read()
end

main()
