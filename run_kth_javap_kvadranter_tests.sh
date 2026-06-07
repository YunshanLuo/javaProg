#!/bin/bash
if [ ! -f kth_javap_kvadranter.java ]; then
    echo "kth_javap_kvadranter.java not found! Please create it to run the tests."
    exit 1
fi

javac kth_javap_kvadranter.java
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

passed=0
total=0

echo "Running tests for kth_javap_kvadranter..."
for test_in in kth_javap_kvadranter_tests/*.in; do
    total=$((total + 1))
    test_name=$(basename "$test_in" .in)
    test_ans="kth_javap_kvadranter_tests/$test_name.ans"
    
    echo -n "Running $test_name... "
    output=$(java kth_javap_kvadranter < "$test_in" | tr -d '\r')
    expected=$(cat "$test_ans" | tr -d '\r')
    
    if [ "$output" = "$expected" ]; then
        echo "PASSED"
        passed=$((passed + 1))
    else
        echo "FAILED"
        echo "  Expected:"
        echo "$expected"
        echo "  Got:"
        echo "$output"
    fi
done

echo "-------------------"
echo "$passed / $total tests passed."
