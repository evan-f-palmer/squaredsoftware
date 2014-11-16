require 'rio'

function fill(n, char)
  local str = {}
  for i = 0, n do table.insert(str, char) end
  return table.concat(str, "")
end

function promptInput(xPrompt)
  rio.write(xPrompt); rio.flush();
  return rio.read()
end

function writeTextToFile(filename, text)
  local out = assert(io.open( filename, "a+" ))
  out:write(text);
  out:flush()
  assert(out:close())
end

function writeTable(t)
  rio.write('{')
  for k,v in pairs(t) do
    writeValue(v)
    if k < #t then rio.write(', ') end
  end
  rio.write('}')
end

function listTable(t, leadLineString)
  local lead  = leadLineString or ""
  for _,v in pairs(t) do
    rio.write(lead)
    writeValue(v)
    rio.write('\n')
  end
end

function writeBoolean(x)
  return x and rio.write("true") or rio.write("false") 
end

local writeValueDispatchTable = {
  ["nil"] = function() rio.write("nil") end,
  boolean = writeBoolean,
  number  = rio.write,
  string  = rio.write,
  table   = writeTable,
}

function writeValue(x)
  writeValueDispatchTable[type(x)](x)
end

function equalTable(x, y)
  for k, v in pairs(x) do
    if not equal(y[k], v) then return false end
  end
  for k, v in pairs(y) do
    if not equal(x[k], v) then return false end
  end
  return true
end

local equalityDispatchTable = {
  ["nil"] = function (x, y) return x==y end,
  boolean = function (x, y) return x==y end,
  number  = function (x, y) return x==y end,
  string  = function (x, y) return x==y end,
  table   = function (x, y) return equalTable(x, y) end,
}

function equal(x, y)
  if type(x) ~= type(y) then return false
  else return equalityDispatchTable[type(x)](x, y)
  end
end

function clear(table)
  local iterationKey = next(table)
  while iterationKey ~= nil do
    table[iterationKey] = nil
    iterationKey = next(table)
  end
  return table
end

function copy(source, destination)
  if not source then return nil end
  if not destination then destination = {} end
  for key, value in pairs(source) do
    rawset(destination, key, value)
  end
  return destination
end
