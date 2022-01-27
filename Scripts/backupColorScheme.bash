#!/bin/bash

#On the system with the colors and settings you want, run:

gconftool-2 --dump '/apps/gnome-terminal' > gnome-terminal-conf.xml

#in this case , i store them in the parent sub-directory "ColorSchemes"
mv gnome-terminal-conf.xml $HOME/Configs/ColorSchemes/
#on the system you want to install the color schemes on run:
#gconftool-2 --load gnome-terminal-conf.xml
