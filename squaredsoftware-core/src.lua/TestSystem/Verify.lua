local expect
local expectFunctions
local expectLogFunction = function(functionType, a, b, message) 
  print("expect_" .. functionType .. " failed. " .. message) 
end

function assertEQ(a, b, message) assert(equal(a,b),     message) end
function assertNE(a, b, message) assert(not equal(a,b), message) end
function assertLT(a, b, message) assert(a < b,          message) end
function assertGT(a, b, message) assert(a > b,          message) end
function assertLE(a, b, message) assert(a <= b,         message) end
function assertGE(a, b, message) assert(a >= b,         message) end

function expectEQ(a, b, message) expect("EQ", a, b, message) end
function expectNE(a, b, message) expect("NE", a, b, message) end
function expectLT(a, b, message) expect("LT", a, b, message) end
function expectGT(a, b, message) expect("GT", a, b, message) end
function expectLE(a, b, message) expect("LE", a, b, message) end
function expectGE(a, b, message) expect("GE", a, b, message) end

expectFunctions = {
  EQ = assertEQ,
  NE = assertNE,
  LT = assertLT,
  GT = assertGT,
  LE = assertLE,
  GE = assertGE
}

expect = function(functionType, a, b, message)
  passed, message = pcall(expectFunctions[functionType], a, b, message)
  if not passed then expectLogFunction(functionType, a, b, message) end
end

  ----------------------
  -- PUBLIC INTERFACE --
  ----------------------

Verify = {
  assertEQ = assertEQ,
  assertNE = assertNE,
  assertLT = assertLT,
  assertGT = assertGT,
  assertLE = assertLE,
  assertGE = assertGE,

  expectEQ = expectEQ,
  expectNE = expectNE,
  expectLT = expectLT,
  expectGT = expectGT,
  expectLE = expectLE,
  expectGE = expectGE,

  expectLogFunction = expectLogFunction,
}
