local pathSeparator        = os.getenv("PATH"):sub(1,1)
local DEF_WIN32, DEF_LINUX = pathSeparator == "\\", pathSeparator == "/"
local thisOS

if DEF_LINUX then
  require "Linux"
  thisOS = Linux
end
if DEF_WIN32 then
  require "Windows"
  thisOS = Windows
end

OS = thisOS

OS_DATA = {
  pathSeparator = pathSeparator,
  DEF_LINUX = DEF_LINUX,
  DEF_WIN32 = DEF_WIN32,
}

-----------------------------------------------------------------------

--local function toDirectory(directory)
--  if directory ~= nil then
--    return DEF_LINUX and 'ls \"' ..directory.. '\"' 
--                      or 'dir \"' ..directory.. '\" /b /ad' -- DEF_WIN32
--  else
--    return DEF_LINUX and 'ls' 
--                      or 'dir /b /ad' -- DEF_WIN32
--  end
--end

--function scandir(directory)
--    local fileTable, popen = {}, io.popen
--    for filename in popen(toDirectory(directory)):lines() do
--        table.insert(fileTable, filename)
--    end
--    return fileTable
--end
