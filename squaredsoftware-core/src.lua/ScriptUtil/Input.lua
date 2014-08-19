local promptAndRead

promptAndRead = function(xPrompt)
    io.write(xPrompt)
    io.flush()
    return io.read("*l")
end

Input = {
  promptAndRead = promptAndRead,
}
