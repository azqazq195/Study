// 객체 심화
let boy = {
  name: "Mike",
  showName: function () {
    console.log(boy.name)
  }
}

let man = boy;
man.name = "Tom"
console.log(boy.name)
man.showName()

boy.name = "Jang"
boy.showName()

boy.name = null
boy.showName()

//

let guy = {
  name: "Mike",
  sayThis: function () {
    console.log(this)
  }
  // sayThis: () => {
  //   console.log(this)
  // }
}
guy.sayThis()




