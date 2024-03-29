$Accent = 'HKCU:\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\Accent'
Set-ItemProperty -path $Accent -Name StartColorMenu -Value 0xff0d0099
Set-ItemProperty -path $Accent -Name AccentColorMenu -Value 0xff2311e8

$d = 0xff,0xbd,0xc2,00,0xff,0x99,0xa1,00,0xf0,0x59,0x65,00,0xe8,0x11,0x23,00,0x99,0x00,0x0d,00,0x6e,0x00,0x09,00,0x47,00,0x06,00,0x69,0x79,0x7e,00
Set-ItemProperty -path $Accent -Name AccentPalette -Value $d

$DWM = 'HKCU:\SOFTWARE\Microsoft\Windows\DWM'
Set-ItemProperty -path $DWM -Name ColorizationColor -Value 0xc4e81123
Set-ItemProperty -path $DWM -Name ColorizationAfterglow -Value 0xc4e81123
Set-ItemProperty -path $DWM -Name AccentColor -Value 0xff2311e8

$P ='HKCU:\SOFTWARE\Microsoft\Windows\CurrentVersion\Themes\Personalize'
Set-ItemProperty -path $P -Name ColorPrevalence -Value 0x00000001
