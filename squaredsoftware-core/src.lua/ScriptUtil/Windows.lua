--require("ScriptUtil/Execute")

--local createTar
--local unTar
--local createDir
--local deleteDir
--local copyDir
--local copyFile
--local deleteFile
--local move
--local lookAt

--createTar = function(xDirToTar, xTarName)
--  return nil
--end

--unTar = function(xTarName)
--  return nil
--end

--createDir = function(xDir)
--  return Execute.executeCmd("mkdir -p " .. xDir)
--end

--deleteDir = function(xDir)
--  return nil
--end

--copyDir = function(xOldLocation, xNewLocation)
--  return nil
--end

--copyFile = function(xOldLocation, xNewLocation)
--  return nil
--end

--deleteFile = function(xFile)
--  return nil
--end

--move = function(xOldLocation, xNewLocation)
--  return nil
--end

--lookAt = function(xPath)
--  return Execute.executeCmd('dir \"' .. xPath .. '\" /b /ad')
--end

--Windows = {
--  createTar  = createTar,
--  unTar      = unTar,
--  createDir  = createDir,  
--  deleteDir  = deleteDir,  
--  copyDir    = copyDir,    
--  copyFile   = copyFile,   
--  deleteFile = deleteFile, 
--  move       = move,
--  lookAt     = lookAt,
--}   
