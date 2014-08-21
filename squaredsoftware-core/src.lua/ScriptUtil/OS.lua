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
