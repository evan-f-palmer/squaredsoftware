local doesDirectoryExist
local doesFileExist

function doesFileExist(xName)
  local f = io.open(xName, "r")
  if f ~= nil then
    io.close(f) 
    return true 
  else 
    return false 
  end
end

doesDirectoryExist = function(xDir)
  return os.execute( "cd " .. xDir )
end

Files = {
  doesDirectoryExist = doesDirectoryExist,
  doesFileExist      = doesFileExist,
}