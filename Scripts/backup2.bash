#other script that backs up my settings, packages, and files that I have installed on a certain system

#Save the currently installed packages list
dpkg --get-selections > ~/Journal/backups/installed_packages.log

#Make a backup of your apt sources file
sudo cp /etc/apt/sources.list ~/backups/sources.bak

#and a copy of your apt's list of trusted keys
sudo apt-key exportall > ~/Journal/backups/repositories.keys
