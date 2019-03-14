---
layout: post
title: "centos zsh"
description: ""
category: 
tags: []
---
{% include JB/setup %}

## Install zsh & oh-my-zsh
`yum -y install zsh`

`git clone git://github.com/robbyrussell/oh-my-zsh.git ~/.oh-my-zsh`

## Edit ~/.zshrc

set the `theme` and set the `plugin`

`vim ~/.zshrc`

```linux
# Path to your oh-my-zsh installation.
export ZSH=$HOME/.oh-my-zsh

ZSH_THEME="dst"

plugins=(git zsh-autosuggestions)

source $ZSH/oh-my-zsh.sh
# autosuggestions
ZSH_AUTOSUGGEST_HIGHLIGHT_STYLE="fg=6"
bindkey '^n' autosuggest-accept
```

## install zsh plugin -- zsh-autosuggestions 

`git clone git://github.com/zsh-users/zsh-autosuggestions /root/.oh-my-zsh/custom/plugins/zsh-autosuggestions`

## change default shell

`chsh -s /bin/zsh`
