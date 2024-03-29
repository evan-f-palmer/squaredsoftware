require("ScriptUtil/Execute")
require("ScriptUtil/OS")

local canReadFile
local doesFileExist
local doesDirectoryExist
local replaceStringWithNewStringInFile
local scanDirectory

local fileModOptions = { read = "r", write = "w", append = "a", binary = "b" }

canReadFile = function(xName)
  local f = io.open(xName, fileModOptions.read)
  if f ~= nil then 
    io.close(f)
    return true 
  else 
    return false 
  end
end

doesFileExist = function(xName)
  return canReadFile(xName)
end

doesDirectoryExist = function(xDir)
  return os.execute( "cd " .. xDir )
end

replaceStringWithNewStringInFile = function(xOldString, xNewString, xFilePath)
  Execute.executeCmd("sed -i \'s/" .. xOldString .. "/" .. xNewString .. "/g\' " .. xFilePath)
end

scanDirectory = function(xDir)
    local fileTable, popen = {}, io.popen
    for filename in popen(OS.lookAt(xDir)):lines() do
        table.insert(fileTable, filename)
    end
    return fileTable
end

Files = {
  canReadFile                      = canReadFile,
  doesFileExist                    = doesFileExist,
  doesDirectoryExist               = doesDirectoryExist,
  replaceStringWithNewStringInFile = replaceStringWithNewStringInFile,
  scanDirectory                    = scanDirectory,
}
