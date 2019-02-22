default: _prelim/*.md */*.md
	tasks/deploy-local.sh

_prelim/prelim%.md: _eboards/eboard%.md
	prelim2 $*

prelim: _prelim/*.md
