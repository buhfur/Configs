#!/bin/bash
#Want to start with filename like Entry1...1+n.txt

#TODO: Change the banner to be smaller and more compact



ENTRY_NUM=1
#creates a filename with the date it was created
FILE=~/Journal/Entries/Entry-`date +%Y%M%d%H%m%S`.txt

BANNER=banner.txt

touch $FILE
sleep 2
echo "Entry file : $FILE has been created\n"
sleep 2
cat $BANNER
echo "Opening entry file...." 
sleep 2
vim $FILE


rm $FILE; echo "removed $FILE"

