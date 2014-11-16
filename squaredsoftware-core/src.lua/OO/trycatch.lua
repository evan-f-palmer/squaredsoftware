require 'Stack'

local canCatch
local isSupposedToCatchFunction

catchStack = Stack()

function try(f, ...)
  if not pcall(f, ...) then
    catchStack:push(f)
  end
end

function catch(f, handler, ...)
  if canCatch() then
    if isSupposedToCatchFunction(f) then
      handler(...)
      catchStack:pop()
    else
      error(catchStack:toString())
    end
  end
end

function trycatch(f, handler)
  try(f) 
  catch(f, handler)
end

---------------------
-- SUPPORT FUNCTIONS
---------------------
function canCatch() return not catchStack:isEmpty() end
function isSupposedToCatchFunction(f) return catchStack:peek() == f end

-- status, ... = xpcall(f, handler)

--local function testing(str)
--  assert(str == 'hello world')
--  print(str)
--end
--local function handler()
--  print("handling")
--end

--try  (testing, 'hello world')
--catch(testing, handler)
--print('---')
--try  (testing, 'hello moon')
--catch(testing, handler)
