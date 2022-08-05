# springboot-jms-sqs
* Spring BootでSpring JMS + AWS SQS Messaging Libraryをつかって、非同期実行依頼を実施するサンプルAPです。
    * 開発端末ローカル実行時は、AP起動時にSQSの代わりにElasticMQを起動して動作します。
## プロジェクト
* sample-web
    * Spring BootのWebアプリケーションで、APIから、非同期実行依頼が可能です。
    * デフォルトspring.profiles.activeがdevになっていて、プロファイルdevの場合は、開発端末ローカル実行を想定していて、AP起動時にSQSの代わりにElasticMQを組み込みで同時に起動するようになっています。
* sample-async
    * Spring Bootの非同期処理アプリケーションで、sample-webが送信した非同期実行依頼のメッセージをSQSを介して受信し処理することが可能です。
    * デフォルトspring.profiles.activeがdevになっていて、プロファイルdevの場合は、開発端末ローカル実行を想定していて、sample-webのAP起動時に、同時に起動されるElasticMQをリッスンするようになっています。

## 動作手順
1. sample-webをSpringBoot Applicationとして起動します。    
2. sample-asyncをSpringBoot Applicationとして起動します。
    * 端末ローカル実行時、ElasticMQをリッスンするので、必ず先にsample-webを起動してから、sample-asyncを起動してください。
3. ブラウザで、以下入力してください。
    * sample-webのAPが、リクエストを受け取り、SQSへ非同期実行依頼のメッセージを送信します。
    * 8080ポートで受け取るので、ローカル実行の場合は以下の通りです。
```
http://localhost:8080/api/v1/async/(Job ID)?param01=(任意文字列)&param02=（任意の文字列）

#ローカル実行の場合の例
http://localhost:8080/api/v1/async/job01?param01=aaa&param02=bbb

#実行後、ブラウザに、以下の応答が返って来ます
{
    result: "accept"
}    
```

4. sample-asyncのAPで、sample-webから受け取ったメッセージ（Job IDとparam01、param02の値）をログ出力します。
    * 本来、受け取ったメッセージをもとにロジック実行しますが、簡単なサンプルなので、ログ出力のみです。

```
#実行後、以下のようなログが出力されます

2022-08-06 07:50:47.197  INFO 15268 --- [ntContainer#0-1] c.example.demo.app.AsyncMessageListener  : ジョブ実行依頼受信[JobId:job01][JobParameter:param01=aaa,param02=bbb]
```    