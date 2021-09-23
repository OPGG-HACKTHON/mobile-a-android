# CHAI Android

<p align="center">
<!-- <img src="https://user-images.githubusercontent.com/54793607/133380331-9af0053a-c2e4-41ce-b2b7-33203cfa8828.png" width=200px height=200px> -->
<img src="https://user-images.githubusercontent.com/54793607/133405364-e2753cdd-812a-4320-b544-80a3886e73ec.jpeg">
</p>


## 차이

<strong>더욱 재밌게 즐기는 리그오브레전드 랭킹, 타이틀 앱 서비스</strong>

> <strong>OPGG 1th HACKATHON</strong><br>
> 프로젝트 기간: 2021.07.05 ~ 09.17

<br>

### <strong>CHAI Android</strong>


|                   김민섭                    |                  이송미                   |
| :-----------------------------------------: | :---------------------------------------: |
| [kminseop90](https://github.com/kminseop90) | [songmilee](https://github.com/songmilee) |

<br>

### <strong>CHAI Used</strong>

<br>

<p>
<img alt="kotlin" src="https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white"/>
<img alt="Android Studio" src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white"/>
<img alt="Github Actions" src="https://img.shields.io/badge/githubactions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white"/>
<br>  
<img alt="retrofit" src="https://img.shields.io/badge/retrofit-2.9.0-brightgreen" />
<img alt="moshi" src="https://img.shields.io/badge/moshi-1.12.0-blue" />
<img alt="hlit_alpha" src="https://img.shields.io/badge/hilt-1.0.0_alpha-green" />
<img alt="glide" src="https://img.shields.io/badge/glide-4.10.0-yellow" />
<img alt="korean indexer" src="https://img.shields.io/badge/korean_indexer-1.15.0-orange" />
<img alt="Android Jetpack" src="https://img.shields.io/badge/android_jetpack-2.3.x-blue" />
</p>

<br>



### CHAI 브랜치 전략

| 브랜치 명 |                     설명                     |
| :-------: | :------------------------------------------: |
|  master   |   실제 배포를 위해 사용되는 브랜치입니다.    |
|  feature  |     신규 기능을 개발하는 브랜치 입니다.      |
|    fix    | 오류가 난 기능을 수정하기 위한 브랜치입니다. |

<br>

- Git Issue를 통해 오류 및 기능 개발을 진행합니다.

<br>

### <strong>CHAI Android Architecture</strong>

![Android Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png?hl=ko)

[출처 : Android 앱 아키텍쳐 가이드](https://developer.android.com/jetpack/guide?hl=ko#recommended-app-arch)

<br>

본 프로젝트는 다음의 패키지를 포함하고 있습니다. 

<br>


| 패키지명 |                             설명                             |
| :------: | :----------------------------------------------------------: |
|    di    |        앱 내에서 모듈에 주입할 컴포넌트를 정의합니다.        |
|  model   | 앱 내에서 사용할 데이터를 정의하고, 데이터 로드에 대해 정의합니다. |
|    ui    |       사용자와 상호작용하는 화면 및 기능을 정의합니다.       |
|   util   |  앱 내에서 반복적으로 사용하는 유용한 기능들을 정의합니다.   |



