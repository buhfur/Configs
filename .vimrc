
set t_Co=256
set nocompatible              " be iMproved, required
set number 
filetype off                  " required 
" set the runtime path to include Vundle and ikitialize

set rtp+=~/.vim/bundle/Vundle.vim
if (empty($TMUX))
  if (has("nvim"))
    "For Neovim 0.1.3 and 0.1.4 < https://github.com/neovim/neovim/pull/2198 >
    let $NVIM_TUI_ENABLE_TRUE_COLOR=1
  endif
  "For Neovim > 0.1.5 and Vim > patch 7.4.1799 < https://github.com/vim/vim/commit/61be73bb0f965a895bfb064ea3e55476ac175162 >
  "Based on Vim patch 7.4.1770 (`guicolors` option) < https://github.com/vim/vim/commit/8a633e3427b47286869aa4b96f2bfc1fe65b25cd >
  " < https://github.com/neovim/neovim/wiki/Following-HEAD#20160511 >
  if (has("termguicolors"))
    set termguicolors
  endif
endif
call vundle#begin()
" alternatively, pass a path where Vundle should install plugins
"call vundle#begin('~/some/path/here')


Plugin 'honza/vim-snippets'
Plugin 'tmsvg/pear-tree'
" let Vundle manage Vundle, required
Plugin 'vim-airline/vim-airline'
Plugin 'VundleVim/Vundle.vim' 
Plugin 'preservim/nerdtree'
Plugin 'morhetz/gruvbox'
Plugin 'grvcoelho/vim-javascript-snippets'

" The following are examples of different formats supported.
" Keep Plugin commands between vundle#begin/end.
" plugin on GitHub repo
" plugin from http://vim-scripts.org/vim/scripts.html
" Plugin 'L9'
" Git plugin not hosted on GitHub
" git repos on your local machine (i.e. when working on your own plugin)
" Pass the path to set the runtimepath properly.
" Install L9 and avoid a Naming conflict if you've already installed a
" different version somewhere else.
" Plugin 'ascenator/L9', {'name': 'newL9'}

" All of your Plugins must be added before the following line
call vundle#end()            " required

"Plug packages used because I coulden't figure out how to make the vundle
call plug#begin()
	Plug 'tpope/vim-fugitive'
	Plug 'tyru/open-browser.vim' "opens urls in browser in vim 
	Plug 'junegunn/fzf', {'do' : { -> fzf#install() }}
	Plug 'neoclide/coc.nvim', {'branch': 'release'} 
	
call plug#end()
"ones work
filetype plugin indent on    " required
" To ignore plugin indent changes, instead use:
"filetype plugin on
"
" Brief help
" :PluginList       - lists configured plugins
" :PluginInstall    - installs plugins; append `!` to update or just :PluginUpdate
" :PluginSearch foo - searches for foo; append `!` to refresh local cache
" :PluginClean      - confirms removal of unused plugins; append `!` to auto-approve removal
"
" see :h vundle for more details or wiki for FAQ
" Put your non-Plugin stuff after this line
"some other commands i might want to add

syntax on
inoremap jj <Esc>
"exit insert mode and save changes
inoremap hh <Esc> :w<CR>

vnoremap <C-c> "*y
set clipboard=unnamed
set clipboard=unnamedplus
"Exit vim if NERDTree is the only window remaining in the only tab.
autocmd BufEnter * if tabpagenr('$') == 1 && winnr('$') == 1 && exists('b:NERDTree') && b:NERDTree.isTabTree() | quit | endif
:set incsearch
command Preview :!firefox %<CR>
" Map F6 to sync project changes to dev server
nmap tt :NERDTreeToggle<CR>

set bg=dark
colorscheme gruvbox   
nnoremap ;w :w<cr>    
nnoremap <space>w :wq<cr>
nnoremap ;q :q!<cr>
"runs scripts with specific mapping [ going to add on to more later like
"javascript
nnoremap cp :call CompileRunGcc()<CR>
func! CompileRunGcc()

	exec "w"
	exec "!clear"
	if &filetype == 'c'
		exec "!gcc % -o %<"
		exec "!time ./%<"
	elseif &filetype == 'cpp'
		exec "!g++ % -o %<"
		exec "!time ./%<"
	elseif &filetype == 'java'
		exec "!time java -cp %:p:h %:t:r"
	elseif &filetype == 'sh'
		exec "!time bash %"
	elseif &filetype == 'python'
		exec "!time python2.7 %"
	elseif &filetype == 'html'
		exec "!firefox % &"
	elseif &filetype == 'go'
		exec "!go build %<"
		exec "!time go run %"
	elseif &filetype == 'md'
		"use grip to preview mardown file 
		"server is configured by default to http://localhost:6419/
		"updates dynamically too! 
		exec "!grip %<"
	endif
endfunc

"Easy access to vimrc
nnoremap vc :vsplit ~/.vimrc<cr>
"reload vimrc
nnoremap rl :source ~/.vimrc<cr>
"function boilerplate code for multple languages
nnoremap <space><space> i#include<stdio.h><Esc>o<CR>int main(void){<Esc>o<Esc>oreturn 0;<Esc>o}<Esc>kki
" updates the NERDTree view and sets it to focus
nmap qq :NERDTreeFocus<cr>R<c-w><c-p>

" switch between tabs with Ctrl + h and Ctrl + l
map <C-L> gt
map <C-H> gT
"create new line below in normal mode with Ctrl + p
"creat new line above with Ctrl + P
"nnoremap <C-o> O<Esc>
"nnoremap <C-p> o<Esc>


nnoremap <C-j> :GFiles<CR>
nmap <leader>gd <Plug>(coc-definition)
nmap <leader>gr <Plug>(coc-references)


"changes [ Ctrl + c ] to work as it does on windows 
"copying text to the system clipboard
noremap <C-c> "*y
noremap <C-V> "*p
vnoremap <C-c> "+y
vnoremap <C-V> "+p
inoremap {<CR> {<CR>}<C-o>O


	
" updates the NERDTree view and sets it to focus
nmap qq :NERDTreeFocus<cr>R<c-w><c-p>

" switch between tabs with Ctrl + h and Ctrl + l
map <C-L> gt
map <C-H> gT
"create new line below in normal mode with Ctrl + p
"creat new line above with Ctrl + P
"nnoremap <C-o> O<Esc>
"nnoremap <C-p> o<Esc>


nnoremap <C-j> :GFiles<CR>
nmap <leader>gd <Plug>(coc-definition)
nmap <leader>gr <Plug>(coc-references)


"changes [ Ctrl + c ] to work as it does on windows 
"copying text to the system clipboard
noremap <C-c> "*y
noremap <C-V> "*p
vnoremap <C-c> "+y
vnoremap <C-V> "+p
inoremap {<CR> {<CR>}<C-o>O
