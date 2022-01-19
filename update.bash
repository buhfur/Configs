#!/usr/bin/bash
#script that updates the Configs repository everytime 
#a change in the bashrc is detected


#list that holds all files to include 

files=("$HOME/.tmux.conf" "$HOME/.vimrc" "$HOME/.bashrc") # this is psuedo code, i have no idea if this works 
# one liner that knows where the script is currently located, the config files are going to be stored here 
DEST_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
#can be changed to send the file to a different directory  
ALT_DIR=""
#concatenates the file to the repository 

for conf in "${files[@]}"
do 
	#cp to Configs/ 
	 
	cp $conf /tmp/
	echo "${conf} successfully copied to $DEST_DIR"

done