import subprocess

def run_test(inputs, expected_output, description):
    print(f"Running test: {description}")
    print(f"  Input: {inputs}")
    
    process = subprocess.Popen(
        ['java', 'morris'],
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True
    )
    
    stdout, stderr = process.communicate(input=inputs)
    
    if stderr:
        print(f"  Error: {stderr}")
        return False
        
    output = stdout.strip()
    print(f"  Output: {output}")
    print(f"  Expected: {expected_output}")
    
    if output == str(expected_output):
        print("  RESULT: PASS")
        print("-" * 30)
        return True
    else:
        print("  RESULT: FAIL")
        print("-" * 30)
        return False

def main():
    # Compile
    print("Compiling morris.java...")
    compile_proc = subprocess.run(['javac', 'morris.java'], capture_output=True, text=True)
    if compile_proc.returncode != 0:
        print("Compilation failed:")
        print(compile_proc.stderr)
        return
    print("Compilation successful.")
    print("=" * 30)

    # Test Cases
    # Sequence: 1, 11, 21, 1211, 111221, 312211, 13112221, ...
    # n=0 -> "1" (0 iterations)
    # n=1 -> "11" (1 iteration)
    # n=2 -> "21"
    # n=3 -> "1211"
    # n=4 -> "111221"
    # n=5 -> "312211"
    
    tests = [
        ("0", "1", "0 iterations (Base case)"),
        ("1", "11", "1 iteration"),
        ("2", "21", "2 iterations"),
        ("3", "1211", "3 iterations"),
        ("4", "111221", "4 iterations"),
        ("5", "312211", "5 iterations"),
        ("6", "13112221", "6 iterations")
    ]

    results = []
    for inputs, expected, desc in tests:
        res = run_test(inputs, expected, desc)
        results.append(res)
    
    print("\nTest Summary:")
    passed = sum(results)
    total = len(tests)
    print(f"{passed}/{total} tests passed.")

if __name__ == "__main__":
    main()
