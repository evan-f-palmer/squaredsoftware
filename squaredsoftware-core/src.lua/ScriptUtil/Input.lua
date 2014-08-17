local promptForInput

promptForInput = function(xPrompt)
    io.write(xPrompt)
    io.flush()
    return io.read("*l")
end

Input = {
  promptForInput = promptForInput,
}