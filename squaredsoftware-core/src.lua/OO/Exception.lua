require 'Class'
----------------------------------------
--  Public Interface
----------------------------------------
Exception = Class({
  message      = "Exception",
  printMessage = function(thisException) print(thisException.message) end,
  throw        = function(thisException) error(thisException.message) end,
})
                     
----------------------------------------
--  Static Globals
---------------------------------------- 
NilReferenceException   = Exception({message = "Nil Reference Exception"})
FileNotFoundException   = Exception({message = "File Not Found"})
NotImplementedException = Exception({message = "Not Implemented"})

--NilReferenceException:throw()
--FileNotFoundException:throw()
--NotImplementedException:throw()
--NilReferenceException:printMessage()
--FileNotFoundException:printMessage()
--NotImplementedException:printMessage()
