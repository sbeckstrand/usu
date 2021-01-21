def wc(files):
    # Open file
    for file in files:
        content = open(file, 'r')

        # Create new variables to start counting the number of words, lines and bytes
        wordCount = 0
        byteCount = 0
        lines = []

        # For each line in our file, append the line to our list, and then count the number of characters in our line.
        for line in content:
            lines.append(line.rstrip('\n'))
            byteCount += len(line)

        # For each word in our line, update the count of our words.
        for word in lines:
            wordCount += 1

        lineCount = len(lines)

        # Print out the count of lines, words and bytes
        print("%d %d %d %s" % (lineCount, wordCount, byteCount, file))
        content.close()
