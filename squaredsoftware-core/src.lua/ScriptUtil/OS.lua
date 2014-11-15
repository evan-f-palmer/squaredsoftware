--local pathSeparator        = os.getenv("PATH"):sub(1,1)
--local DEF_WIN32, DEF_LINUX = pathSeparator == "\\", pathSeparator == "/"
local System = luajava.bindClass("java.lang.System");

local osName = System:getProperty("os.name"):lower()
local pathSeparator = System:getProperty("file.separator")
local DEF_LINUX = osName == "linux"
local DEF_WIN32 = osName == "windows"
local thisOS

if DEF_LINUX then
  require "ScriptUtil/Linux"
  thisOS = Linux
end

if DEF_WIN32 then
  require "ScriptUtil/Windows"
  thisOS = Windows
end

OS = thisOS

OS_DATA = {
  pathSeparator = pathSeparator,
  DEF_WIN32     = DEF_WIN32,
  DEF_LINUX     = DEF_LINUX,
  osName        = osName,
}
