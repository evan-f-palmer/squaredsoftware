echo "SquaredSoftwareGitId = \"" > src.lua/SquaredSoftwareGitId.lua
/usr/bin/git rev-parse HEAD >> src.lua/SquaredSoftwareGitId.lua
echo "\"" >> src.lua/SquaredSoftwareGitId.lua
sed -i ':a;N;$!ba;s/\n/\t/g' src.lua/SquaredSoftwareGitId.lua
