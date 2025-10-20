class IOUtils:
    def promptForInt(message, validOptions):
        isValidInput = False
        while (not isValidInput):
            try:
                output = int(input(message))
                if (output in validOptions):
                    isValidInput = True
                    break
                else:
                    print('Invalid Input')
                    continue
            except:
                print('Invalid Input')
        return output