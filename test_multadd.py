import subprocess
import os
import shutil

def run_test(inputs, expected_output, description):
    print(f"Running test: {description}")
    print(f"Input: {inputs}")
    
    process = subprocess.Popen(
        ['java', 'multadd'],
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True
    )
    
    stdout, stderr = process.communicate(input=inputs)
    
    if stderr:
        print(f"Error: {stderr}")
        return False
        
    output = stdout.strip()
    print(f"Output: {output}")
    print(f"Expected: {expected_output}")
    
    if output == str(expected_output):
        print("PASS")
        return True
    else:
        print("FAIL")
        return False
    print("-" * 20)

def main():
    # Setup: Always update multadd.java from multadd
    if os.path.exists("multadd"):
        shutil.copy("multadd", "multadd.java")
        print("Updated multadd.java from multadd")
    else:
        print("Error: multadd file not found.")
        return

    # Compile
    print("Compiling...")
    compile_proc = subprocess.run(['javac', 'multadd.java'], capture_output=True, text=True)
    if compile_proc.returncode != 0:
        print("Compilation failed:")
        print(compile_proc.stderr)
        return
    print("Compilation successful.")
    print("-" * 20)

    tests = [
        ("1", 1, "Single number"),
        ("1 2", 3, "Two numbers (1+2 > 1*2)"),
        ("2 3", 6, "Two numbers (2*3 > 2+3)"),
        ("1 2 3", 7, "Three numbers (1+2*3 = 7 vs 1*2+3 = 5)"),
        ("1 2 3 4", 14, "Four numbers (even). 1*2 + 3*4 = 14"), # 1+2*3+4 = 11. 
        ("1 2 3 4 5", 27, "Five numbers (odd). 1 + 2*3 + 4*5 = 27"), # 1*2 + 3*4 + 5 = 19
        ("0 0 0", 0, "Zeros"),
        ("1 1 1", 2, "Ones (1+1*1 = 2)"),
        ("10 2 5", 20, "10 2 5 -> 10*2+5 = 25 or 10+2*5=20? Wait. Alternating mult/add. 10*2 + 5 = 25? OR 10 + 2*5 = 20. Max is 25.")
    ]
    
    # Wait, let's re-calculate expectations based on the problem description carefully.
    # "start with multiplication or addition alternating each time"
    # Sequence: a b c d e
    # Option 1: Mult first: a*b + c*d + e...
    # Option 2: Add first: a + b*c + d*e...
    
    # "1 2 3" -> 3 nums
    # Mult first: 1*2 + 3 = 5
    # Add first: 1 + 2*3 = 7
    # Max: 7. Correct.
    
    # "1 2 3 4" -> 4 nums
    # Mult first: 1*2 + 3*4 = 2 + 12 = 14
    # Add first: 1 + 2*3 + 4 = 1 + 6 + 4 = 11
    # Max: 14. Correct.
    
    # "1 2 3 4 5" -> 5 nums
    # Mult first: 1*2 + 3*4 + 5 = 2 + 12 + 5 = 19
    # Add first: 1 + 2*3 + 4*5 = 1 + 6 + 20 = 27
    # Max: 27. Correct.
    
    # "1 1 1"
    # mult: 1*1 + 1 = 2
    # add: 1 + 1*1 = 2
    # Max 2. Correct.
    
    # "10 2 5"
    # mult: 10*2 + 5 = 25
    # add: 10 + 2*5 = 20
    # Max 25.
    
    # Adjust "10 2 5" expectation to 25.
    
    tests = [
        ("1", 1, "Single number"),
        ("1 2", 3, "Two numbers"),
        ("1 2 3", 7, "Three numbers"),
        ("1 2 3 4", 14, "Four numbers"),
        ("1 2 3 4 5", 27, "Five numbers"),
        ("1 1 1", 2, "Ones"),
        ("10 2 5", 25, "10 2 5")
    ]

    results = []
    for inputs, expected, desc in tests:
        res = run_test(inputs, expected, desc)
        results.append(res)
    
    print("\nSummary:")
    passing = sum(results)
    print(f"{passing}/{len(tests)} tests passed.")

if __name__ == "__main__":
    main()
