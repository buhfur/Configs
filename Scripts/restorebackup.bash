#!/bin/bash


#Start by restoring the sources file from the backup made 
sudo cp $HOME/Journal/backups/sources.bak /etc/apt/sources.list

#The backed-up keys 
sudo apt-key add $HOME/Journal/backups/repositories.keys

#Update your sources lists 
sudo apt-get update

#Restore the packages from the saved installed_packages.log 
sudo dpkg --clear-selections
sudo dpkg --set-selections < $HOME/Journal/backups/installed_packages.log && sudo apt-get dselect-upgrade

