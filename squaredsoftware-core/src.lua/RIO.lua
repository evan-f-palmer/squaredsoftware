---------------------
-- Redirectable I/O
---------------------

local getInputStream
local getOutputStream
local redirectInputStream
local redirectOutputStream
local concatOpenOutputStream
local write
local writeln
local flush
local read
local readall

function getInputStream() return io.input() end
function getOutputStream() return io.output() end

function redirectInputStream(instream)
  assert(io.input():close());
  io.input(instream);
end

function redirectOutputStream(outstream)
  assert(io.output():close());
  io.output(outstream);
end

function concatOpenOutputStream(stream) return assert(io.open( stream, "a+" )) end

function write(...) io.write(...) end
function writeln(...) io.write(...); io.write("\n"); end
function flush() io.flush() end
function read() io.read()       end
function readall() io.read("*all") end

  ----------------------
  -- PUBLIC INTERFACE --
  ----------------------
  
RIO = {
  
  getInputStream          = getInputStream,
  getOutputStream         = getOutputStream,
  redirectInputStream     = redirectInputStream,
  redirectOutputStream    = redirectOutputStream,
  concatOpenOutputStream  = concatOpenOutputStream,
  write                   = write,
  writeln                 = writeln,
  flush                   = flush,
  read                    = read,
  readall                 = readall,
  
}

