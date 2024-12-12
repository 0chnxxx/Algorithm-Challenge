
num_rows = 5


new_rows = []
for i in range(num_rows):
    row = [1] * (i + 1)

    for j in range(1, i):
        row[j] = new_rows[i - 1][j - 1] + new_rows[i - 1][j]

    new_rows.append(row)

print(new_rows)


