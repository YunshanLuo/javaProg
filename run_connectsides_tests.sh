#!/bin/bash
javac connectsides.java
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

passed=0
total=0

echo "Running tests for connectsides..."
for test_in in connectsides_tests/*.in; do
    total=$((total + 1))
    test_name=$(basename "$test_in" .in)
    test_ans="connectsides_tests/$test_name.ans"
    
    echo -n "Running $test_name... "
    output=$(java connectsides < "$test_in" | tr -d '\r')
    expected=$(cat "$test_ans" | tr -d '\r')
    
    if [ "$output" = "$expected" ]; then
        echo "PASSED"
        passed=$((passed + 1))
    else
        echo "FAILED"
        echo "  Expected: '$expected'"
        echo "  Got:      '$output'"
    fi
done

echo "-------------------"
echo "$passed / $total tests passed."
