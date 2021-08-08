// 배열

let days = ["mon", "tue", "wed"]
days[1] = "화요일"

// [ 'mon', '화요일', 'wed' ]
console.log(days)

days.push("thu")            // 뒤에 넣기
days.unshift("sun")   // 앞에 넣기

// [ 'sun', 'mon', '화요일', 'wed', 'thu' ]
console.log(days)

for(let i=0; i<days.length; i++)
  console.log(days[i])

for(let day of days)
  console.log(day)

