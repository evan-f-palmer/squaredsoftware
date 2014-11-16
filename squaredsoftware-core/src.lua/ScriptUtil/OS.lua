--[[
  https://docs.oracle.com/javase/7/docs/api/java/lang/System.html
]]--

local getOS
local sys = luajava.bindClass('java.lang.System')
local os_name = sys:getProperty("os.name")

DEF_LINUX = string.match(os_name, "Linux")
DEF_WIN32 = not DEF_LINUX

OS               = getOS()
OS.name          = os_name
OS.arch          = sys:getProperty("os.arch")
OS.version       = sys:getProperty("os.version")
OS.pathSeparator = sys:getProperty("path.separator")
OS.fileSeparator = sys:getProperty("file.separator")
OS.lineSeparator = sys:getProperty("line.separator")
OS.username      = sys:getProperty("user.name")      -- User's account name
OS.userhome      = sys:getProperty("user.home")      -- User's home directory
OS.userdir       = sys:getProperty("user.dir")       -- User's current working directory

function getOS()
  if      DEF_LINUX then require "Linux";   return Linux;
  elseif  DEF_WIN32 then require "Windows"; return Windows; 
  else                                      return {};
  end
end 
