$Accent = 'HKCU:\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\Accent'
Set-ItemProperty -path $Accent -Name StartColorMenu -Value 0xff9e5a00
Set-ItemProperty -path $Accent -Name AccentColorMenu -Value 0xffd77800

$d = 0xa6,0xd8,0xff,0x00,0x76,0xb9,0xed,0x00,0x42,0x9c,0xe3,0x00,0x00,0x78,0xd7,0x00,0x00,0x5a,0x9e,0x00,0x00,0x42,0x75,0x00,0x00,0x26,0x42,0x00,0xf7,0x63,0x0c,0x00
Set-ItemProperty -path $Accent -Name AccentPalette -Value $d

$DWM = 'HKCU:\SOFTWARE\Microsoft\Windows\DWM'
Set-ItemProperty -path $DWM -Name ColorizationColor -Value 0xc40078d7
Set-ItemProperty -path $DWM -Name ColorizationAfterglow -Value 0xc40078d7
Set-ItemProperty -path $DWM -Name AccentColor -Value 0xffd77800

$P ='HKCU:\SOFTWARE\Microsoft\Windows\CurrentVersion\Themes\Personalize'
Set-ItemProperty -path $P -Name ColorPrevalence -Value 0x00000000
