#!/usr/bin/sh
#script that updates the Configs repository everytime 
#a change in the bashrc is detected


#list that holds all files to include 


#concatenates the file to the repository 
echo 
cat vimrc > ~/.vimrc
cat bashrc > ~/.bashrc 
