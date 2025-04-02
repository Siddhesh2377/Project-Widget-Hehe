import os

#Hello I am Just a Android Dev, i am not very skilled in using PY but i will try :)

#Created a lamda fun for clearing the terminal for noice view :)
clear = lambda: os.system('clear')
#Again...!, calling that lamda here :)
clear()

#Some Show-Bazhi
print("######## File Analyzer CLI Tool ########\n")
print("Starting analysis...\n")

#Serious Point : All IMP operations here ...!

#Reading the File & saving it in a var call file
file = open("Objetives.txt")
data = file.read().lower()
file.close()

#Spliting the String into words and getting the total num of words present : Completing the first task
wordCounts = len(data.split())
print(">> Total Number of Words: {}\n".format(wordCounts))

#Now it's time for the Second Task " The Top 5 frequently used words !! Ofc Excluding : ignore common stop words like \"the\", \"is\", \"and\""
words = []
for w in data.split():
    #Here we will flush out those bad words (: "the", "is", "and") Meaning Here you just have to add the words you want to ignore and done
    if w not in ("the", "is", "and", "a"):
        #Hehe Removed !!!
        words.append(w)

#Here comes the new var named counts obviously to store and count the words
counts = []
for unique in set(words):
    count = 0
    for word in words:
        #Simple Logic if it contains the word then ++ by 1 :)
        if word == unique:
            count += 1
    counts.append((count, unique))

#Haha Let's Stort !!, The List ofc
counts.sort()
#And Let's Reverse it tooo, Other Vise ...!
counts.reverse()

print(">> Top 5 Frequently Used Words (Excluding stop words):")
# Print Top 5 Candidates ( Ahmmm, I mean Words ) with the highest counts.
for i in range(min(5, len(counts))):
    count, word = counts[i]
    print("   {} : {}".format(word, count))

print("\n-------------------------\n")

#Hmmmm, Lets move to the next Operation: The Average word length. !!!!!!
words = data.split()
# Some Complext Math Here...
avg_length = sum(len(word) for word in words) / len(words) if words else 0

# Now i will Count sentences by counting '.', '!' and '?'. as it is the most simple methods for finding a sentence
sentence_count = data.count('.') + data.count('!') + data.count('?')

print(">> Average Word Length: {:.2f}".format(avg_length))
print(">> Number of Sentences: {}".format(sentence_count))
print("\nHehe....,Analysis Complete!, How was it ahhh ?? : )")
