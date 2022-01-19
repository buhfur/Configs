#!/bin/bash

#one liner that says where bash and script are at all times
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

ls $SCRIPT_DIR/rpc
