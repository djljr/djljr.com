---
name: Static Site Generation With Clojure
description: Tools and techniques used to generate and deploy this static website.
author: Dennis Lipovsky
author-email: dennis.lipovsky@gmail.com
date-created: 2017-03-13
draft: true
type: post
keywords: clojure, perun, static website generator, aws, s3, hosting
---
## Building static websites using Clojure



### Perun

### Confetti

#### AWS Woes

```text
dlipovsky@TURING:~/src/djljr.com$ boot -d confetti fetch-outputs --access-key AKIAIFJVN6OQQAENJTHA --secret-key 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 --confetti-edn djljr-com
Fetching outputs for djljr-com.confetti.edn... Stack status is other than CREATE_COMPLETE: CREATE_IN_PROGRESS
{:stack-info {:description Created by github.com/confetti-clj, :capabilities [CAPABILITY_IAM], :tags [], :disable-rollback false, :notification-arns [], :stack-name djljr-com, :stack-status CREATE_IN_PROGRESS, :stack-id arn:aws:cloudformation:us-east-1:122953312376:stack/djljr-com/95ba2fb0-085c-11e7-a095-500c20ff1436, :outputs [], :parameters [{:parameter-value djljr.com, :parameter-key UserDomain}], :creation-time #object[org.joda.time.DateTime 0x64d947aa 2017-03-14T02:19:00.020Z]}}

dlipovsky@TURING:~/src/djljr.com$ boot -d confetti fetch-outputs --access-key AKIAIFJVN6OQQAENJTHA --secret-key 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 --confetti-edn djljr-com
Fetching outputs for djljr-com.confetti.edn... Stack status is other than CREATE_COMPLETE: ROLLBACK_IN_PROGRESS
{:stack-info {:description Created by github.com/confetti-clj, :capabilities [CAPABILITY_IAM], :tags [], :disable-rollback false, :notification-arns [], :stack-name djljr-com, :stack-status ROLLBACK_IN_PROGRESS, :stack-id arn:aws:cloudformation:us-east-1:122953312376:stack/djljr-com/95ba2fb0-085c-11e7-a095-500c20ff1436, :outputs [], :parameters [{:parameter-key UserDomain, :parameter-value djljr.com}], :creation-time #object[org.joda.time.DateTime 0x5bcb5d47 2017-03-14T02:19:00.020Z]}}
```

Looing at CloudFormation in the AWS console I can see the issue issue

```text
22:44:45 UTC-0400    CREATE_FAILED   AWS::Route53::HostedZone   HostedZone   Rate exceeded
```

It took a good hour to fail and rollback the create-site.
