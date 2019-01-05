#!/usr/bin/env bash
apidoc -i ../login-playground-app/src -o apidoc && docker build -t aedwa038/playground-docs:latest . && sh clean.sh
