require("ScriptUtil/Execute")

local createTar
local unTar

createTar = function(xDirToTar, xTarName)
  return Execute.executeCmd("tar czf " .. xTarName .. " " .. xDirToTar)
end

unTar = function(xTarName)
  return Execute.executeCmd("tar -zxf " .. xTarName)
end

Linux = {
  createTar       = createTar,
  unTar           = unTar,
}