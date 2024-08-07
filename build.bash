#!/bin/bash

for dir in ./*/; do
  if [ -d "$dir" ]; then
    echo "Building $dir"
    # change to the directory and run the command
    cd "$dir" || continue

    # Verification of the existence of the mvnw file
    if [ -f "./mvnw" ]; then
      # Run the command
      ./mvnw clean package -DskipTests
    else
      echo "Not found mvnw file in $dir"
    fi
    # Regresa a la ruta base
    cd - > /dev/null || exit
  fi
done

echo "BUILD SUCCESS"
