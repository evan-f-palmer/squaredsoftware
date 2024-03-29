require("ScriptUtil/Execute")

local createTar
local unTar
local createDir
local deleteDir
local copyDir
local copyFile
local deleteFile
local move
local lookAt

createTar = function(xDirToTar, xTarName)
  return Execute.executeCmd("tar czf " .. xTarName .. " " .. xDirToTar)
end

unTar = function(xTarName)
  return Execute.executeCmd("tar -zxf " .. xTarName)
end

createDir = function(xDir)
  return Execute.executeCmd("mkdir -p " .. xDir)
end

deleteDir = function(xDir)
  return Execute.executeCmd("rm -rf " .. xDir)
end

copyDir = function(xOldLocation, xNewLocation)
  return Execute.executeCmd("cp -r " .. xOldLocation .. " " .. xNewLocation)
end

copyFile = function(xOldLocation, xNewLocation)
  return Execute.executeCmd("cp  " .. xOldLocation .. " " .. xNewLocation)
end

deleteFile = function(xFile)
  return Execute.executeCmd("rm  " .. xFile)
end

move = function(xOldLocation, xNewLocation)
  return Execute.executeCmd("mv  " .. xOldLocation .. " " .. xNewLocation)
end

lookAt = function(xPath)
  return Execute.executeCmd('ls \"' .. xPath .. '\"')
end

Linux = {
  createTar  = createTar,
  unTar      = unTar,
  createDir  = createDir,  
  deleteDir  = deleteDir,  
  copyDir    = copyDir,    
  copyFile   = copyFile,   
  deleteFile = deleteFile, 
  move       = move,
  lookAt     = lookAt,
}   
