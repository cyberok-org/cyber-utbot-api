# Taint Parameters Standard

Параметры для taint анализа представлены в формате `json` файлов. Текущее место хранения - `resources/org/cyber/utbot/api/taint`

### Sources

```json
{
  "containingClass": "Lorg/example/Example;",
  "signature": "source()I",
  "description": "source example",
  "taintsThis": "false",
  "taintsReturn": "true",
  "taintsArgs": [],
  "taintsGlobals": []
}
```

`containingClass` – fqn класса, содержащего функцию `source`  
`signature` – bytecode signature функции  
`description` – описание функции  
`taintsThis` – будет ли вызывающий инстанс tainted  
`taintsReturn` – будет ли return из данной функции tainted  
`taintsArgs` – сет tainted аргументов, аргумент представлен своим порядковым номером  
`taintsGlobals` – сет tainted глобальных переменных, переменные представлены своими именами

### Sinks

```json
{
  "containingClass": "Lorg/example/Example;",
  "signature": "sink(I)V",
  "description": "sink example",
  "takesInstance": "false",
  "takesArgs": [1],
  "takesGlobals": []
}
```

`containingClass` – fqn класса, содержащего функцию `sink`  
`signature` – bytecode signature функции  
`description` – описание функции  
`takesInstance` – будет ли sink чувствителен к вызывающему его инстансу  
`takesArgs` – сет чувствительных к taint-у аргументов  
`takesGlobals` – сет чувствительных к taint-у глобальных переменных  

`*` в данном контексте "`x` чувствителен к `y`" означает, если `y` tainted, то мы считаем, что в `x` что-то может пойти не так из-за `y`
